const db = require("../models");
const Task = db.tasks;
const Op = db.Sequelize.Op;

// Create and Save a new Task
exports.create = (req, res) => {
  // Validate request
  if (!req.body.title || !req.body.description || !req.body.done) {
    res.status(400).send({
      message: "Content can not be empty!"
    });
    return;
  }

  // Create a Task
  const task = {
    title: req.body.title,
    description: req.body.description,
    done: req.body.done
  };

  // Save Task in the database
  Task.create(task)
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while creating the task."
      });
    });
};

// Retrieve all Tasks from the database.
exports.findAll = (req, res) => {
    Task.findAll()
      .then(data => {
        res.send(data);
      })
      .catch(err => {
        res.status(500).send({
          message:
            err.message || "Some error occurred while retrieving bicycles."
        });
      });
};

// Find a single Task with an id
exports.findOne = (req, res) => {
  let id = req.params.id;
  Task.findByPk(id)
    .then(data => {
      if (!data) {
        res.status(404).send({
          message: "There's no task with that id"
        });
      }
      res.send(data);
      return;
    })
    .catch(err => {
      console.log(err.message)
      res.status(500).send({
        message:
          err.message || "Some error occurred while retrieving a bicycle."
      });
      return;
    })
};

// Update a Task by the id in the request
exports.update = (req, res) => {
  const id = req.params.id;

  Task.update(req.body, {
    where: { id: id }
  })
    .then(num => {
      if (num == 1) {
        res.send({
          message: "Task was updated successfully."
        });
      } else {
        res.send({
          message: `Cannot update Task with id=${id}. Maybe Task was not found or req.body is empty!`
        });
      }
    })
    .catch(err => {
      res.status(500).send({
        message: "Error updating Task with id=" + id
      });
    });
};

// Delete a Task with the specified id in the request
exports.delete = (req, res) => {
  const id = req.params.id;

  Task.destroy({
    where: { id: id }
  })
    .then(num => {
      if (num == 1) {
        res.send({
          message: "Task was deleted successfully!"
        });
      } else {
        res.send({
          message: `Cannot delete Task with id=${id}. Maybe Task was not found!`
        });
      }
    })
    .catch(err => {
      res.status(500).send({
        message: "Could not delete Task with id=" + id
      });
    });
};

// Delete all Task from the database.
exports.deleteAll = (req, res) => {
  Task.destroy({
    where: {},
    truncate: false
  })
    .then(nums => {
      res.send({ message: `${nums} Task were deleted successfully!` });
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while removing all Task."
      });
    });
};

// Retrieve all tasks currently undone
exports.findAllUndone = (req, res) => {
  Task.findAll({ where: { done: false } })
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

// Retrieve all tasks currently done
exports.findAllDone = (req, res) => {
  Task.findAll({ where: { done: true } })
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

// Retrieve all tasks currently undone
exports.copyTask = (req, res) => {
  let id = req.params.id;
  let currentId = Task.findByPk(id)
    .then(data => {
      if (!data) {
        res.status(404).send({
          message: "There's no task with that id"
        });
      }
      res.send(data);
      return;
    })
    .catch(err => {
      console.log(err.message)
      res.status(500).send({
        message:
          err.message || "Some error occurred while retrieving a bicycle."
      });
      return;
    })
  
  const copiedTask = {
    title: currentId.title,
    description: currentId.description,
    done: "false"
  };

  // Save Task in the database
  Task.create(copiedTask)
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while creating the task."
      });
    });
};