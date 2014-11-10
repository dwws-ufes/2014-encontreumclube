var geocoder;
var map;

/* Função de inicialização */
function initialize() {
	
	geocoder = new google.maps.Geocoder(); // Atualiza mapa de acordo com localização
	var mapOptions = {
		center:new google.maps.LatLng(-19.570142,-40.464363),
		zoom:7,
		mapTypeId:google.maps.MapTypeId.ROADMAP
	};
	map = new google.maps.Map(document.getElementById('gmap'), mapOptions);
	
	setMarkers(map, markers); // Adiciona marcadores ao mapa	
			
	geoLocation();
	
} //Fecha initialize()


/*--------------------------------------------------------------------
Adicionar marcadores ao mapa*/
function setMarkers(map, locations) {
  for (var i=0; i<locations.length; i++) {
    var marker = new google.maps.Marker({
        position: new google.maps.LatLng(locations[i][1], locations[i][2]),
        map: map,
        icon: 'resources/images/icon.png',
        title: locations[i][0]
    });
    // Cria infowindow
    var infowindow = new google.maps.InfoWindow();
    google.maps.event.addListener(marker, 'click', (function(marker, i) {
        return function() {
        	map.setCenter(marker.getPosition());
        	map.setZoom(16);
        	infowindow.setContent(locations[i][3]);
        	infowindow.open(map, marker);
        };
    })(marker, i));
    //Cria sidebar
    /*var ul = document.getElementById("mapList");
	var li = document.createElement("li");
	li.innerHTML = locations[i][3];
	ul.appendChild(li);  
	google.maps.event.addDomListener(li, 'click', (function(marker, i) {
        return function() {
        	google.maps.event.trigger(marker, "click");
        };
    })(marker, i));*/
  }
}


/*-------------------------------------------------------------------
  GeoCode */
function codeAddress() { 
	  var address = document.getElementById('j_idt8:address').value;
	  if (address.length > 0) {
		  geocoder.geocode( { 'address': address}, function(results, status) {
		    if (status == google.maps.GeocoderStatus.OK) {
		      map.setCenter(results[0].geometry.location);
		      map.setZoom(13);
		    } else {
		      geoLocation();
		    }
		  });
		  searchAddress(address, markers);
	  }
}

/* Encontra string em endereços de marcadores */
function searchAddress(address, locations) {
	var markers = new Array;
	for (var i=0; i<locations.length; i++) {
		if (locations[i][3].toLowerCase().indexOf(address.toLowerCase()) > -1)
			markers.push(locations[i]);
	}
	if (markers.length == 0){
		document.getElementById("mapList").innerHTML = "<h4> Resultados para <b>" + address + "</b></h4><br> Nenhum clube para esta localiza&#231;&#227;o.";
	} else
		showMarkers(address, map, markers);
}

/* Mostra marcadores  para um dado endereço */
function showMarkers(address, map, locations) {
	  for (var i=0; i<locations.length; i++) {
	    var marker = new google.maps.Marker({
	        position: new google.maps.LatLng(locations[i][1], locations[i][2]),
	        map: map,
	        icon: 'resources/images/icon.png',
	        title: locations[i][0]
	    });
	    // Cria infowindow
	    var infowindow = new google.maps.InfoWindow();
	    google.maps.event.addListener(marker, 'click', (function(marker, i) {
	        return function() {
	        	map.setCenter(marker.getPosition());
	        	map.setZoom(16);
	        	infowindow.setContent(locations[i][3]);
	        	infowindow.open(map, marker);
	        };
	    })(marker, i));
	    //Cria sidebar
	    var elem = document.getElementById("mapList");
	    if (i == 0) {
	    	elem.innerHTML = "<h4> Resultados para <b>" + address + "</b></h4><br>";
	    }    
		var li = document.createElement("li");
		li.innerHTML = locations[i][3];
		elem.appendChild(li);  
		google.maps.event.addDomListener(li, 'click', (function(marker, i) {
	        return function() {
	        	google.maps.event.trigger(marker, "click");
	        };
	    })(marker, i));
	  }
}


/*--------------------------------------------------------------------
GeoLocation */
function geoLocation() {
	if(navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(function(position) {
			var pos = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
			map.setZoom(12);
			map.setCenter(pos);
			codeLatLng(position.coords.latitude, position.coords.longitude);
		});
	};
}

/* Encontra  o endereço com base na lattidude e longitude */
function codeLatLng(lat, lng) {
    var latlng = new google.maps.LatLng(lat, lng);
    geocoder.geocode({'latLng': latlng}, function(results, status) {
      if (status == google.maps.GeocoderStatus.OK) {
        if (results[2]) {
        	//alert (results[2].formatted_address);
        	var split = results[2].formatted_address.split(" ");
        	address = split[0];
        	searchAddress(address, markers);
        }
      }
    });
  }

/*--------------------------------------------------------------------
  Inicia mapa */
google.maps.event.addDomListener(window, 'load', initialize);