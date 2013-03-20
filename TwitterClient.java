import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import twitter4j.*;

public class TwitterClient {

	public static void main(String[] args) {


		try{
			//Check command line parameters
			if(args.length == 1 && args[0].equals("-timeline")){
				showTimeline();
			}
			else if (args.length == 2 && args[0].equals("-tweet")) {
				sendTweet(args[1]);
				
			}
			else{
				//Display usage info
				System.out.println("Usage:");
				System.out.println("java TwitterClient -timeline");
				System.out.println("  Display your timeline");
				System.out.println("java TwitterClient -tweet [YOUR TWEET]");
				System.out.println("  Send a tweet!");
			}
		}
		catch(Exception e){
			System.out.println(e);
			e.printStackTrace();
		}
		

	}
	static void showTimeline(){

		try {

	    // gets Twitter instance with default credentials
			Twitter twitter = new TwitterFactory().getInstance();
			User user = twitter.verifyCredentials();
			List<Status> statuses = twitter.getHomeTimeline();
			// display current Teets in my stream of messages
			System.out.println("Showing @" + user.getScreenName() + "'s home timeline.");
			for (Status status : statuses) {
				System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
			}
		}

		catch (TwitterException te) {
			
			te.printStackTrace();
			System.out.println("Failed to get timeline: " + te.getMessage());
			
		}
	}

	static void sendTweet(String tweetMessage){

		try{
			Twitter twitter = new TwitterFactory().getInstance();
			User user = twitter.verifyCredentials();
			Tweet myTweet = new Tweet();
			//if tweet is valid, send it
			if(myTweet.isValidMessage(tweetMessage)){
				myTweet.setMessage(tweetMessage);
				Status status = twitter.updateStatus(myTweet.getMessage());
				System.out.println("Successfully updated status to [" + status.getText() + "].");
			}
			else{
				System.out.println("Your tweet is not valid (140 characters or less, please!).");
			}
			
		}
		catch(TwitterException te){
			te.printStackTrace();
			System.out.println("Failed to send tweet: " + te.getMessage());
		}

	}
} 

