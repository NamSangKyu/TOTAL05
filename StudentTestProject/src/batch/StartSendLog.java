package batch;

public class StartSendLog {
	SendLogCronTrriger trigger;

	public static void main(String[] args)  { 
		SendLogCronTrriger trigger = new SendLogCronTrriger("0 0 0 1/1 * ? *", SendLogJob.class);
		trigger.triggerJob();
    }
	
}
