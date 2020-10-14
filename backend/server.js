const express = require("express");
const bodyParser = require("body-parser");
const cors = require("cors");

const app = express();

var corsOptions = {
  origin: "http://localhost:8081"
};

app.use(cors(corsOptions));

// parse requests of content-type - application/json
app.use(bodyParser.json());

// parse requests of content-type - application/x-www-form-urlencoded
app.use(bodyParser.urlencoded({ extended: true }));

const db = require("./app/models");

// explotation time.
//db.sequelize.sync();

// development time only. Drop and re-sync db.
db.sequelize.sync({ force: true }).then(() => {
    console.log("Drop and re-sync db.");
  });

  require("./app/routes/bicycle.routes")(app);

// simple route
app.get("/", (req, res) => {
  res.json({ message: "Welcome to bezkoder application." });
});

app.get("/asdf", (req, res) => {
    res.json({ message: "Welcome to the fucking hell." });
})

// set port, listen for requests
const PORT = process.env.PORT || 8080;
app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}.`);
});