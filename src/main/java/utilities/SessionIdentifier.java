package utilities;

import org.springframework.stereotype.Component;

@Component
public class SessionIdentifier {

    private String sessionId;
    private String csrfId;

    public SessionIdentifier(){

    }

    public String getSessionId(){
        return this.sessionId;
    }
    public String getCsrfId(){
        return this.csrfId;
    }
    public void setSessionId(final String sessionId){
        this.sessionId = sessionId;
    }
    public void setCsrfId(final String csrfId){
        this.csrfId = csrfId;
    }

    public boolean equals(final Object o){
        if(o== this){
            return true;
        }
        else if (!(o instanceof  SessionIdentifier)){
            return false;
        } else{
            SessionIdentifier other = (SessionIdentifier)o;
            if(!other.canEqual(this)){
                return false;
            }else{
                Object this$sessionId = this.getSessionId();
                Object other$sessionId = other.getSessionId();
                if(this$sessionId == null){
                    if(other$sessionId != null){
                        return false;
                    }
                } else if(!this$sessionId.equals(other$sessionId)){
                    return false;
                }
                Object this$csrfId = this.getCsrfId();
                Object other$csrfId = other.getCsrfId();
                if(this$csrfId == null){
                    if(other$csrfId != null){
                        return false;
                    }
                } else if (!this$csrfId.equals(other$csrfId)){
                    return false;
                }
                return true;
            }
        }
    }

    protected boolean canEqual(final Object other){
        return other instanceof SessionIdentifier;
    }

    public int hashCode(){
//        int PRIME = true;
        int result = 1;
        Object $sessionId = this.getSessionId();
        result = result * 59+($sessionId == null ? 43 : $sessionId.hashCode());
        Object $csrfId = this.getCsrfId();
        return result;
    }

    public String toString(){
        return "SessionIdentifiers(sessionId=" +this.getSessionId()+", csrfId="+this.getCsrfId() +")";
    }
}
