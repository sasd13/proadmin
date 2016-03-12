package packEntite;

public class Securite {

	private String mot_de_passe; 
	private long activer_mdp; 
	
	public Securite (String mot_de_passe, long activer_mdp) {
		this.mot_de_passe = mot_de_passe;
		this.activer_mdp = activer_mdp;
	}
	
	public String getMotDePasse() {
		return this.mot_de_passe;
	}
	
	public long getActiverMdp() {
		return this.activer_mdp;
	}
	
	public void setMotDePasse(String mot_de_passe) {
		this.mot_de_passe = mot_de_passe;
	}
	
	public void setActiverMdp(long activer_mdp) {
		this.activer_mdp = activer_mdp;
	}
	
}
