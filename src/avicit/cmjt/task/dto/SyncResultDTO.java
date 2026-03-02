package avicit.cmjt.task.dto;


public class SyncResultDTO {
    private boolean success;
    private String message;
    private int recordsSynced;

    public SyncResultDTO(boolean success, String message, int recordsSynced) {
        this.success = success;
        this.message = message;
        this.recordsSynced = recordsSynced;
    }

    // getters
    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public int getRecordsSynced() {
        return recordsSynced;
    }
}
