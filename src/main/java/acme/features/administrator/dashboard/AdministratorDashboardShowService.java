package acme.features.administrator.dashboard;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.Status;
import acme.forms.administrator.AdministratorDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, AdministratorDashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorDashboardRepository repository;

	// AbstractShowService<Patron, PatronDashboard> interface ----------------


	@Override
	public boolean authorise(final Request<AdministratorDashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public AdministratorDashboard findOne(final Request<AdministratorDashboard> request) {
		assert request != null;
		
		AdministratorDashboard result;
		final int numberOfProposedPatronages;
		final int numberOfAcceptedPatronages;
		final int numberOfDeniedPatronages;
		
		final int numberOfTools;
		
		final int numberOfComponents;
		
		numberOfProposedPatronages=this.repository.numberOfPatronages(Status.PROPOSED);
		numberOfAcceptedPatronages=this.repository.numberOfPatronages(Status.ACCEPTED);
		numberOfDeniedPatronages=this.repository.numberOfPatronages(Status.DENIED);
		
		numberOfTools = this.repository.numberOfTools();
		
		numberOfComponents = this.repository.numberOfComponents();
		
		
		List<AdministratorDashboardItem> statsBudgetofProposedPatronages;
		List<AdministratorDashboardItem> statsBudgetofAcceptedPatronages;
		List<AdministratorDashboardItem> statsBudgetofDeniedPatronages;	
		
		List<AdministratorDashboardItem> statsRetailPriceofTools;
		
		List<AdministratorDashboardComponentItem> statsRetailPriceofComponents;
		
		
		statsBudgetofProposedPatronages=this.getStatisticsOfPatronages(Status.PROPOSED);
		statsBudgetofAcceptedPatronages=this.getStatisticsOfPatronages(Status.ACCEPTED);
		statsBudgetofDeniedPatronages=this.getStatisticsOfPatronages(Status.DENIED);
		
		statsRetailPriceofTools=this.getStatisticsOfTools();
		
		statsRetailPriceofComponents=this.getStatisticsOfComponents();
		
		result= new AdministratorDashboard();
		result.setNumberOfProposedPatronages(numberOfProposedPatronages);
		result.setNumberOfAcceptedPatronages(numberOfAcceptedPatronages);
		result.setNumberOfDeniedPatronages(numberOfDeniedPatronages);
		result.setStatsBudgetofAcceptedPatronages(statsBudgetofAcceptedPatronages);
		result.setStatsBudgetofProposedPatronages(statsBudgetofProposedPatronages);
		result.setStatsBudgetofDeniedPatronages(statsBudgetofDeniedPatronages);
		
		result.setNumberOfTools(numberOfTools);
		result.setStatsRetailPriceOfTools(statsRetailPriceofTools);
		
		result.setNumberOfComponents(numberOfComponents);
		result.setStatsRetailPriceOfComponents(statsRetailPriceofComponents);
		
		return result;
	}

	@Override
	public void unbind(final Request<AdministratorDashboard> request, final AdministratorDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "numberOfProposedPatronages", "numberOfAcceptedPatronages", "numberOfDeniedPatronages", "statsBudgetofProposedPatronages", "statsBudgetofAcceptedPatronages", "statsBudgetofDeniedPatronages","numberOfTools","statsRetailPriceOfTools","numberOfComponents","statsRetailPriceOfComponents");
		
	}	
	
	
	private List<AdministratorDashboardItem> getStatisticsOfPatronages(final Status status){
		
		final List<String> currencies= (List<String>) this.repository.currencies();
		final List<AdministratorDashboardItem> res = new ArrayList<AdministratorDashboardItem>();
		
		for(int i=0;i<currencies.size();i++) {
			final String currency = currencies.get(i);
			
			final AdministratorDashboardItem itemStats = new AdministratorDashboardItem();
			itemStats.currency=currency;
			itemStats.average= this.repository.averagePatronage(status, currency);
			itemStats.deviation= this.repository.deviationPatronage(status, currency);
			itemStats.min=this.repository.minimunPatronage(status, currency);
			itemStats.max=this.repository.maximunPatronage(status, currency);
			
			res.add(itemStats);
		}
		
		return res;
		
	}
	
	private List<AdministratorDashboardItem> getStatisticsOfTools(){
		
		final List<String> currencies= (List<String>) this.repository.currencies();
		final List<AdministratorDashboardItem> res = new ArrayList<AdministratorDashboardItem>();
		
		for(int i=0;i<currencies.size();i++) {
			final String currency = currencies.get(i);
			
			final AdministratorDashboardItem itemStats = new AdministratorDashboardItem();
			itemStats.currency=currency;
			itemStats.average= this.repository.averageRetailPriceOfTools(currency);
			itemStats.deviation= this.repository.deviationRetailPriceOfTools(currency);
			itemStats.min=this.repository.minimumRetailPriceOfTools(currency);
			itemStats.max=this.repository.maximumRetailPriceOfTools(currency);
			
			res.add(itemStats);
		}
		
		return res;
		
	}
	
private List<AdministratorDashboardComponentItem> getStatisticsOfComponents(){
		
		final List<String> currencies= (List<String>) this.repository.currencies();
		final List<AdministratorDashboardComponentItem> res = new ArrayList<AdministratorDashboardComponentItem>();
		
		final List<String> technologies= (List<String>) this.repository.technologies();
		
		for(int j = 0;j<technologies.size();j++) {
			final String technology = technologies.get(j);
		
		
			for(int i=0;i<currencies.size();i++) {
				final String currency = currencies.get(i);
				
				final AdministratorDashboardComponentItem itemStats = new AdministratorDashboardComponentItem();
				itemStats.technology = technology;
				itemStats.currency=currency;
				itemStats.average= this.repository.averageRetailPriceOfComponents(currency,technology);
				itemStats.deviation= this.repository.deviationRetailPriceOfComponents(currency,technology);
				itemStats.min=this.repository.minumumRetailPriceOfComponents(currency,technology);
				itemStats.max=this.repository.maximumRetailPriceOfComponents(currency,technology);
				
				res.add(itemStats);
			}
		}
		return res;
		
	}

}