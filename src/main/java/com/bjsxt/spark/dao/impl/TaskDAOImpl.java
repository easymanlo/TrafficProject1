package com.bjsxt.spark.dao.impl;

import java.sql.ResultSet;

import com.bjsxt.spark.dao.ITaskDAO;
import com.bjsxt.spark.domain.Task;
import com.bjsxt.spark.jdbc.JDBCHelper;
import com.bjsxt.spark.jdbc.JDBCHelper.QueryCallback;

/**
 * 任务管理DAO实现类
 * @author root
 *
 */
public class TaskDAOImpl implements ITaskDAO {
	//这个task就是传入了一个 tariff数据库中的task表中的一个  task_id
	@Override
	public Task findTaskById(long taskId) {
		final Task task = new Task();
	
		String sql = "SELECT * FROM task WHERE task_id = ?";
		
		Object[] params = new Object[]{taskId};
		
		JDBCHelper jdbcHelper = JDBCHelper.getInstance();
		jdbcHelper.executeQuery(sql, params, new QueryCallback() {
			//new QueryCallback() 这个是一个接口对象。一个匿名对象）
			@Override
			public void process(ResultSet rs) throws Exception {
				if(rs.next()) {
					long taskid = rs.getLong(1);
					String taskName = rs.getString(2);
					String createTime = rs.getString(3);
					String startTime = rs.getString(4);
					String finishTime = rs.getString(5);
					String taskType = rs.getString(6);
					String taskStatus = rs.getString(7);
					String taskParam = rs.getString(8);
					
					task.setTaskId(taskid);
					task.setTaskName(taskName); 
					task.setCreateTime(createTime); 
					task.setStartTime(startTime);
					task.setFinishTime(finishTime);
					task.setTaskType(taskType);  
					task.setTaskStatus(taskStatus);
					task.setTaskParams(taskParam);  
				}
			}
		});
		return task;
	}

}
