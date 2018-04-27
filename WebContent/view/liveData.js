function liveData(ticker) {
	var urlString = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=" + 
					ticker.toUpperCase() + "&interval=1min&apikey=WLWSM9CTLOJXEQXU&datatype=json";
     
	dataObj = JSON.parse(httpGet(urlString));
	var data = [];
	
	// extracting the key to use according to frequency
	var key = ""; var i =0;
	for(x in dataObj) {
		if(i==1)
			key = x;
		i++;
	}
	
	for(x in dataObj[key]){
		obj = dataObj[key][x];
		finalObj = {
				date: x,
				open: obj["1. open"],
				close: obj["4. close"],
		};
		data.push(finalObj);	
	}
	
	return data[0].close;
}

function httpGet(theUrl)
{
    var xHttp = new XMLHttpRequest();
    xHttp.open( "GET", theUrl, false ); // false for synchronous request
    xHttp.send();
    return xHttp.response;
}