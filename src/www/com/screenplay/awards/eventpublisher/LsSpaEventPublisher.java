/**
 * 
 */
package www.com.screenplay.awards.eventpublisher;



import www.com.screenplay.awards.event.LaSpaEmailEventImpl;
import www.com.screenplay.awards.util.ContextProvider;

/**
 * @author sarans
 *
 */

public class LsSpaEventPublisher{
	public void publish(LaSpaEmailEventImpl event){
		ContextProvider.getApplicationContext().publishEvent(event);
	}
}
