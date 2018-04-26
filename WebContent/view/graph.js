function createGraph(data) {
	d3.select("svg").remove();
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
				high: obj["2. high"],
				low: obj["3. low"],
				close: obj["4. close"],
				volume: obj["5. volume"]	
		};
		data.push(finalObj);	
	}
	
	
	var margin = {top: 20, right: 50, bottom: 30, left: 50},
    width = 960 - margin.left - margin.right,
    height = 500 - margin.top - margin.bottom;
	
	var requiredTimeFormat = "";  
	var entries = 0;
	switch(key){
		case "Time Series (1min)":
			requiredTimeFormat = "%Y-%m-%d %H:%M:%S";
			break;
		case "Time Series (Daily)":
			requiredTimeFormat = "%Y-%m-%d";
			entries = 30;
			break;
		case "Weekly Time Series":
			requiredTimeFormat = "%Y-%m-%d";
			entries = 52;
			break;
		case "Monthly Time Series":
			requiredTimeFormat = "%Y-%m-%d";
			break;		
	}
	
	var parseDate = d3.timeParse(requiredTimeFormat);
	
	var x = techan.scale.financetime()
    .range([0, width]);

	var y = d3.scaleLinear()
	    .range([height, 0]);
	
	var candlestick = techan.plot.candlestick()
	    .xScale(x)
	    .yScale(y);
	
	var xAxis = d3.axisBottom(x);
	
	var xTopAxis = d3.axisTop(x);
	
	var yAxis = d3.axisLeft(y);
	
	var yRightAxis = d3.axisRight(y);
	
	 var ohlcAnnotation = techan.plot.axisannotation()
     	.axis(yAxis)
     	.orient('left')
     	.format(d3.format(',.2f'));

	var ohlcRightAnnotation = techan.plot.axisannotation()
	     .axis(yRightAxis)
	     .orient('right')
	     .translate([width, 0]);
	
	var timeAnnotation = techan.plot.axisannotation()
	     .axis(xAxis)
	     .orient('bottom')
	     .format(d3.timeFormat(requiredTimeFormat))
	     .width(65)
	     .translate([0, height]);
	
	var timeTopAnnotation = techan.plot.axisannotation()
	     .axis(xTopAxis)
	     .orient('top');
	
	var crosshair = techan.plot.crosshair()
	     .xScale(x)
	     .yScale(y)
	     .xAnnotation([timeAnnotation, timeTopAnnotation])
	     .yAnnotation([ohlcAnnotation, ohlcRightAnnotation])
	     .on("enter", enter)
	     .on("out", out)
	     .on("move", move);
	
	var svg = d3.select("body").append("svg")
	     .attr("width", width + margin.left + margin.right)
	     .attr("height", height + margin.top + margin.bottom)
	     .append("g")
	     .attr("transform", "translate(" + margin.left + "," + margin.top + ")");
	
	var coordsText = svg.append('text')
	     .style("text-anchor", "end")
	     .attr("class", "coords")
	     .attr("x", width - 5)
	     .attr("y", 15);
	
	var accessor = candlestick.accessor();
	data = data.map(function(d) {
        return {
            date: parseDate(d.date),
            open: +d.open,
            high: +d.high,
            low: +d.low,
            close: +d.close,
            volume: +d.volume
        };
    }).sort(function(a, b) { return d3.ascending(accessor.d(a), accessor.d(b)); });
	
	var newData = [];
	if(entries != 0) {
		for(i = data.length - 1, j = (entries - 1); i >= (data.length - entries); i--, j--)
			newData[j] = data[i];
		data = newData;
	}
	
	x.domain(data.map(accessor.d));
    y.domain(techan.scale.plot.ohlc(data, accessor).domain());

    svg.append("g")
            .datum(data)
            .attr("class", "candlestick")
            .call(candlestick);

    svg.append("g")
            .attr("class", "x axis")
            .call(xTopAxis);

    svg.append("g")
            .attr("class", "x axis")
            .attr("transform", "translate(0," + height + ")")
            .call(xAxis);

    svg.append("g")
            .attr("class", "y axis")
            .call(yAxis);

    svg.append("g")
            .attr("class", "y axis")
            .attr("transform", "translate(" + width + ",0)")
            .call(yRightAxis);

    svg.append("g")
            .attr("class", "y annotation left")
            .datum([{value: 74}, {value: 67.5}, {value: 58}, {value:40}]) // 74 should not be rendered
            .call(ohlcAnnotation);

    svg.append("g")
            .attr("class", "y annotation right")
            .datum([{value: 61}, {value:52}])
            .call(ohlcRightAnnotation);

    svg.append('g')
            .attr("class", "crosshair")
            .datum({ x: x.domain()[80], y: 67.5 })
            .call(crosshair)
            .each(function(d) { move(d); }); // Display the current data

    svg.append('text')
            .attr("x", 5)
            .attr("y", 15)
            .text(dataObj["Meta Data"]["2. Symbol"]);
    function enter() {
        coordsText.style("display", "inline");
    }

    function out() {
        coordsText.style("display", "none");
    }

    function move(coords) {
        coordsText.text(
            timeAnnotation.format()(coords.x) + ", " + ohlcAnnotation.format()(coords.y)
        );
    }
	
}