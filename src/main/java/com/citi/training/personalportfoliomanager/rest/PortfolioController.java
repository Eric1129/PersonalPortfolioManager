package com.citi.training.personalportfoliomanager.rest;


import com.citi.training.personalportfoliomanager.entities.Portfolio;
import com.citi.training.personalportfoliomanager.service.PortfolioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/portfolio")
public class PortfolioController {

    @Autowired
    private PortfolioServices portfolioService;

    /**
     * Gets the account with id account_id
     */
    @RequestMapping(method = RequestMethod.GET, value="/{account_id}")
    public Portfolio getAccount(@PathVariable("account_id") int account_id){
        return portfolioService.get(account_id);
    }

    @RequestMapping(method = RequestMethod.GET, value="/networth")
    public Portfolio getNetWorth(){
        return portfolioService.getNetWorth();
    }

    @RequestMapping(method = RequestMethod.GET, value="/cashvalue")
    public Portfolio getNetWorth(){
        return portfolioService.getCashValue();
    }

    @RequestMapping(method = RequestMethod.GET, value="/investmentvalue")
    public Portfolio getNetWorth(){
        return portfolioService.getInvestmentValue();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addAccount(@RequestBody CompactDisc disc) {
        service.addNewCompactDisc(disc);
    }

}
