public class Account {
		public String identifier;
        private String accountKind;
        private String ammount;

        public Account(String identifier, String accountKind, String ammount) {
            super();
            this.identifier = identifier;
            this.accountKind = accountKind;
            this.ammount = ammount;
        }
        
        public float getAmmount() {
        	return Float.parseFloat(this.ammount);
        }

        public void deposit(float ammount) {
            this.ammount = Float.toString( this.getAmmount() + ammount );
        }

        public void withdraw(float ammount) {
            this.ammount = Float.toString( this.getAmmount() - ammount );
        }

        public String[] toJson(){
        	String[] json = { this.identifier, this.accountKind, this.ammount };
        	return json;
        }

    	public String info() {
    		return 	"Tipo da conta: "+this.accountKind+", \n"+
    				"Valor na conta: "+this.ammount+", \n"
    		;
    	}
        
    }
