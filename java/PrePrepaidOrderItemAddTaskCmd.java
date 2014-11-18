/**************************************************************
 * Classname: PrePrepaidOrderItemAddTaskCmd
 * Description:The implementation of this command constructs 
 * the orderitem.field4 parameter and also determines if the
 * catentryId can be added to cart
 * Copyright Notice : Copyright (C) 2013 Airtel, All Rights Reserved
 * @author shashi
 **************************************************************/
package com.ibm.sf.commerce.orderitems.commands;

import java.util.Hashtable;

import com.ibm.commerce.command.TaskCommand;

public interface PrePrepaidOrderItemAddTaskCmd extends TaskCommand {

//default 

	static final String defaultCommandClassName = "com.ibm.sf.commerce.orderitems.commands.PrePrepaidOrderItemAddTaskCmdImpl";
//still testing git
	public void setIhshPrepaidCatentry_id(Hashtable<Integer, String> ihshPrepaidCatentry_id);
	
	public Hashtable<Integer, String> getHshOrderItemField2();

}
