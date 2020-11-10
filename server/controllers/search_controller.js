const axios = require("axios"); 

module.exports.get_geocoding = async (req, res) => {
  // given query string for location, make request to some google maps api to get geocoding for that location 
  // send geocoding + other info back to java client

  console.log(req.body); 

  // READ url params (country will always be USA)
  // TODO

  // GET REQUEST TO GOOGLE API FOR GEOCODE
  // TODO

  // RESPONSE WITH GEOCODE FOR LOCATION
  // TODO

  
  res.send({success: true, message: "testing! this is response from server"}); 
}