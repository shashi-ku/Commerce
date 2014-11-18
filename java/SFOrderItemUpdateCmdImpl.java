/**************************************************************
 * Classname:SFOrderItemUpdateCmdImpl
 * Description:This command extends the OOB OrderItemAddCmdImpl
 * to implement custom business logic during to decrease the
 * quantity of orderitem passed in request by 1.
 * Copyright Notice : Copyright (C) 2013 Airtel, All Rights Reserved
 * @author shashi
 **************************************************************/
package com.ibm.sf.commerce.orderitems.commands;

import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.Map;
import java.util.logging.Logger;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.naming.NamingException;

import org.apache.commons.lang.StringUtils;

import com.ibm.commerce.command.CommandFactory;
import com.ibm.commerce.datatype.TypedProperty;
import com.ibm.commerce.exception.ECApplicationException;
import com.ibm.commerce.exception.ECException;
import com.ibm.commerce.exception.ECSystemException;
import com.ibm.commerce.order.objects.OrderItemAccessBean;
import com.ibm.commerce.order.utils.OrderConstants;
import com.ibm.commerce.order.utils.ResolveParameter;
import com.ibm.commerce.orderitems.commands.OrderItemUpdateCmdImpl;
import com.ibm.commerce.ras.ECMessage;
import com.ibm.commerce.ras.ECMessageHelper;
import com.ibm.commerce.requisitionlist.commands.RequisitionListItemUpdateCmd;
import com.ibm.sf.commerce.common.constants.SFConstants;
import com.ibm.sf.commerce.ras.SFMessage;

public class SFOrderItemUpdateCmdImpl extends OrderItemUpdateCmdImpl {
	private static final String CLASSNAME = PreOrderItemAddCmdImpl.class.getName();
	private static final Logger LOGGER = Logger.getLogger(CLASSNAME);
	private boolean isRequisitionListFlow = false;
	private Hashtable<Integer, String> orderItemId = null;

	@Override
	public void setRequestProperties(TypedProperty requestProperties) throws ECException {
		final String methodName = "setRequestProperties";
		isRequisitionListFlow = requestProperties.getBoolean(SFConstants.IS_REQUISITIONLISTFLOW, false);

		if (isRequisitionListFlow) {
			// requisition list flow
			orderItemId = ResolveParameter.resolveValues(SFConstants.EC_ORDERITEM_RN, requestProperties, false);

		} else {
			super.setRequestProperties(requestProperties);
		}

	}

	@Override
	public void validateParameters() throws ECException {
		final String methodName = "validateParameters";
		LOGGER.entering(CLASSNAME, methodName);
		if (!isRequisitionListFlow) {
			super.validateParameters();
		}
		LOGGER.exiting(CLASSNAME, methodName);
	}

	/**
	 * This method is responsible for reducing the quantity by 1 for the
	 * orderitem ids of requisitionlist passed in request for MYOP flow.
	 * 
	 */
	@Override
	public void performExecute() throws ECException {
		final String methodName = "performExecute";
		LOGGER.entering(CLASSNAME, methodName);
		if (!isRequisitionListFlow) {
			super.performExecute();
		} else {

			if (null != orderItemId && !orderItemId.isEmpty()) {
				TypedProperty requisitionListUpdateInputProp = new TypedProperty();
				requisitionListUpdateInputProp.put(SFConstants.EC_STATUS,
						OrderConstants.REQUISITION_LIST_STATUS_PRIVATE);
				requisitionListUpdateInputProp.put(SFConstants.EC_URL, SFConstants.EC_FORWARD_SLASH);
				OrderItemAccessBean orderItemAccessBean = null;
				try {
					for (Map.Entry<Integer, String> entry : orderItemId.entrySet()) {

						orderItemAccessBean = new OrderItemAccessBean();
						if (StringUtils.isEmpty(entry.getValue())) {
							TypedProperty hshNVPs = new TypedProperty();
							hshNVPs.put(SFConstants.EC_ERROR_CODE, SFMessage.SF_ERROR_CODE_ORDERITEM_ID_MISSING);
							throw new ECApplicationException(SFMessage.SF_ERR_MISSING_PARAMETER, CLASSNAME, methodName,
									ECMessageHelper.generateMsgParms(OrderConstants.EC_ORDERITEM_ID), hshNVPs);

						}
						orderItemAccessBean.setInitKey_orderItemId(entry.getValue());

						orderItemAccessBean.refreshCopyHelper();
						requisitionListUpdateInputProp.put(OrderConstants.EC_ORDERITEM_ID.concat(
								SFConstants.STRING_HYPHEN).concat(entry.getKey().toString()), entry.getValue());
						// reduce the quantity of order item by 1
						requisitionListUpdateInputProp.put(OrderConstants.EC_QUANTITY.concat(SFConstants.STRING_HYPHEN)
								.concat(entry.getKey().toString()), orderItemAccessBean.getQuantityInEJBType() - 1);

					}

					RequisitionListItemUpdateCmd requisitionListItemUpdateCmd = (RequisitionListItemUpdateCmd) CommandFactory
							.createCommand("com.ibm.commerce.requisitionlist.commands.RequisitionListItemUpdateCmd",
									getCommandContext().getStoreId());
					requisitionListItemUpdateCmd.setRequestProperties(requisitionListUpdateInputProp);
					requisitionListItemUpdateCmd.setCommandContext(getCommandContext());
					requisitionListItemUpdateCmd.execute();

					if (null == responseProperties) {
						responseProperties = new TypedProperty();
					}
					String[] arrOrderIds = requisitionListItemUpdateCmd.getOrderIds();
					String[] arrOrderItemIds = requisitionListItemUpdateCmd.getOrderItemIds();
					if (null != arrOrderIds && arrOrderIds.length > 0) {
						responseProperties.put(SFConstants.EC_ORDER_RN, arrOrderIds);
					}
					if (null != arrOrderItemIds && arrOrderItemIds.length > 0) {
						responseProperties.put(SFConstants.EC_ORDERITEM_RN, arrOrderItemIds);
					}

				} catch (RemoteException e) {
					throw new ECSystemException(ECMessage._ERR_REMOTE_EXCEPTION, CLASSNAME, methodName, e);
				} catch (CreateException e) {
					throw new ECSystemException(ECMessage._ERR_CREATE_EXCEPTION, CLASSNAME, methodName, e);
				} catch (FinderException e) {
					TypedProperty hshNVPs = new TypedProperty();
					hshNVPs.put(SFConstants.EC_ERROR_CODE, SFMessage.SF_ERROR_CODE_ORDERITEM_ID_INVALID);
					throw new ECApplicationException(SFMessage.SF_ERR_INVALID_PARAMETER, CLASSNAME, methodName,
							ECMessageHelper.generateMsgParms(SFConstants.EC_ORDERITEM_RN), hshNVPs);
				} catch (NamingException e) {
					throw new ECSystemException(ECMessage._ERR_NAMING_EXCEPTION, CLASSNAME, methodName, e);
				} catch (NumberFormatException e) {
					TypedProperty hshNVPs = new TypedProperty();
					hshNVPs.put(SFConstants.EC_ERROR_CODE, SFMessage.SF_ERROR_CODE_ORDERITEM_ID_INVALID);
					throw new ECApplicationException(SFMessage.SF_ERR_INVALID_PARAMETER, CLASSNAME, methodName,
							ECMessageHelper.generateMsgParms(SFConstants.EC_ORDERITEM_RN), hshNVPs);

				}

			}
		}
		LOGGER.exiting(CLASSNAME, methodName);
	}

	@Override
	public void checkResourcePermission() throws ECException {
		final String methodName = "checkResourcePermission";
		LOGGER.entering(CLASSNAME, methodName);
		if (!isRequisitionListFlow) {
			super.checkResourcePermission();
		} else {
			return;
		}
		LOGGER.exiting(CLASSNAME, methodName);
	}

}
