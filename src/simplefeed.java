import net.cosm2sml.CosmFeed;
import net.cosm2sml.helper.impl.CosmFeedHelperImpl;
import net.cosm2sml.sensorml.sensorML;

public class simplefeed {

	public static void main(String args[]) throws Exception {
		CosmFeedHelperImpl helper = new CosmFeedHelperImpl();
		CosmFeed feed = helper.parseFeed(126768);
		sensorML ml = helper.decodeFeed(feed);
		//use ml
	}

}
