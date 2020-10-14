const db = require("../models");
const Bicycle = db.bicycles;
const Op = db.Sequelize.Op;

// Create and Save a new Bicycle
exports.create = (req, res) => {
  // Validate request
  if (!req.body.brand || !req.body.model) {
    res.status(400).send({
      message: "Content can not be empty!"
    });
    return;
  }

  // Create a Bicycle
  const bicycle = {
    brand: req.body.brand,
    model: req.body.model
  };

  // Save Bicycle in the database
  Bicycle.create(bicycle)
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while creating the Tutorial."
      });
    });
};

// Retrieve all Bicycles from the database.
exports.findAll = (req, res) => {
    
    Bicycle.findAll()
      .then(data => {
        res.send(data);
      })
      .catch(err => {
        res.status(500).send({
          message:
            err.message || "Some error occurred while retrieving tutorials."
        });
      });
};

// Find a single Bicycle with an id
exports.findOne = (req, res) => {
  
};

// Update a Bicycle by the id in the request
exports.update = (req, res) => {
  
};

// Delete a Bicycle with the specified id in the request
exports.delete = (req, res) => {
  
};

// Delete all Bicycle from the database.
exports.deleteAll = (req, res) => {
  
};

// Find all published Bicycle
exports.findAllPublished = (req, res) => {
  
};
