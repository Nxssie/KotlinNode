module.exports = app => {
    const tasks = require("../controllers/task.controller.js");
  
    var router = require("express").Router();
  
    // Create a new task
    router.post("/", tasks.create);
  
    // Retrieve all tasks
    router.get("/", tasks.findAll);
  
    // Retrieve a single task with id
    router.get("/:id", tasks.findOne);
  
    // Update a task with id
    router.put("/:id", tasks.update);
  
    // Delete a task with id
    router.delete("/:id", tasks.delete);
  
    // Delete all tasks
    router.delete("/", tasks.deleteAll);

    // Retrieve all undone tasks
    router.get("/undone/all", tasks.findAllUndone);

    // Retrieve all done tasks
    router.get("/done/all", tasks.findAllDone);

    // Duplicate an existing task
    //router.post("/copy/:id", tasks.copyTask);
  
    app.use('/api/tasks', router);
  };