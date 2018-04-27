function liveData(data) {
	dataObj = JSON.parse(data);
	var data = [];
	
	console.log(dataObj)
	
	// extracting the key to use according to frequency
	var key = "";
	for(x in dataObj) {
		key = x;
		break;
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
	console.log(data[0].close);
}