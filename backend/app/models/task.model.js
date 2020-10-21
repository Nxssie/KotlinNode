module.exports = (sequelize, Sequelize) => {
    const Task = sequelize.define("task", {
      title: {
        type: Sequelize.STRING(30)
      },
      description: {
        type: Sequelize.STRING
      },
      done: {
        type: Sequelize.BOOLEAN
      }
    }, {timestamps : false});
  
    return Task;
  };