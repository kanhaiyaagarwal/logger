package logger;

import amazon.internal.dropship.core.thirdpartylogistics.reports.OpenPOReportJobHandler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import amazon.internal.dropship.common.messaging.MessagePropertyType;
import amazon.internal.dropship.common.messaging.messagequeue.DefaultMessageObject;
import amazon.internal.dropship.common.messaging.messagequeue.MessageKey;
import amazon.internal.dropship.common.messaging.messagequeue.MessageQueue;
import amazon.internal.dropship.core.messaging.messagequeue.DropShipMessageQueues;
 f
warehouseId="ILGI";
    String resp="";
    OpenPOReportJobHandler opa=new OpenPOReportJobHandler();
    opa.setWarehouseId(warehouseId);
    opa.resetBatchId();

    DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date startDate=format.parse("2018-10-12 08:33:13");
    Date endDate=format.parse("2019-10-12 20:33:15");

    Set<String> orderIds=new HashSet<>();
//get message to be sent to sedi
    report=opa.executeReport(startDate,endDate,1000l);

    if(CollectionUtils.isNotEmpty(reportList)){
    DefaultMessageObject message=
    new DefaultMessageObject(opa.getReportMessageType(),opa.getWarehouseId(),0);

    MessageQueue queue=
    DropShipMessageQueues.getEIOutgoing(opa.getWarehouseId(),message.getMessageType());

    if(queue==null){
    return"PO Job unable to locate EI Outgoing Queue!  It is unclear why this happens, "
    +"remedy is to bounce the box.";

    }
    for(POReportObject poReportObject:reportList){
    StringReader reader=new StringReader(report);
    Map<MessagePropertyType, List<String>>propertyMap=new HashMap<>();
    propertyMap.put(MessagePropertyType.PO_NUMBER_ID,new LinkedList<>(orderIds));
    MessageKey key=queue.enqueue(message,reader,true,null,propertyMap,opa.getBatchId());

    resp=resp+"Completed Open PO Report and enqueued "+queue.toString(key);
    }
    }
    return resp;