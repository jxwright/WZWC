package avicit.cmjt.task;

import avicit.cmjt.task.service.CmSyncService;
import avicit.platform6.core.spring.SpringFactory;

public class SyncContract {

    /**
     * 更新合同台账相关信息
     */
    public void execute(){
        CmSyncService cmSyncService = SpringFactory.getBean("cmSyncService");
        cmSyncService.syncCmLedgerData("cmContract");
    }

    /**
     * 更新标的相关信息
     */
    public void executeCmjtSubjectBaseTask(){
        CmSyncService cmSyncService = SpringFactory.getBean("cmSyncService");
        cmSyncService.syncCmjtSubjectBaseData("cmjtSubjectBase");
    }

    public void executeCmjtOrderTask(){
        CmSyncService cmSyncService = SpringFactory.getBean("cmSyncService");
        cmSyncService.syncCmjtOrderData("cmjtOrder");
    }
}
