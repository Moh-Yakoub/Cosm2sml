This is an intial stage of developing a parser for cosm sensors and transform it to sensorML , to use it :

<pre><code>CosmFeedHelperImpl helper = new CosmFeedHelperImpl();
	   CosmFeed feed = helper.parseFeed(feed it);
           CosmSensorML ml = helper.decodeFeed(feed);
	   SensorMLDocumentEncoder encoder = new SensorMLDocumentEncoderImpl();</code></pre>

<p>You need to add your api key to the <pre><code>Constants</code></pre> class
