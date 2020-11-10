/*
  Andreas G. 
  our java client will connect to this server/ make requests with a query 
*/ 

const express = require("express");
const app = express(); 
const PORT = process.env.PORT || 8000; 
require("dotenv").config();
//const axios = require("axios");

app.use(express.json());
app.use(express.urlencoded({ extended: true }));  

const router = require("./routes");
app.use("/api", router); 

app.listen(PORT); 