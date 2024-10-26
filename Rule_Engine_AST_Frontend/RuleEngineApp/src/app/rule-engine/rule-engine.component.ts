// src/app/rule-engine.component.ts
import { AfterViewInit, Component, ElementRef, ViewChild, ViewChildren } from '@angular/core';
import { RuleService } from '../rule.service'
@Component({
  selector: 'app-rule-engine',
  templateUrl: './rule-engine.component.html',
  styleUrls: ['./rule-engine.component.css']
})
export class RuleEngineComponent implements AfterViewInit{
  @ViewChild('createRuleButton') createRuleButton!: ElementRef;
  ruleString: string = '';
  ruleIds: string = '';
  megaRuleId: number | null = null;
  dataJson: string = '';
  modifyRuleId: number | null = null;
  newRuleString: string = '';
  output: string[] = [];

  constructor(private ruleService: RuleService) {}
  
  ngAfterViewInit() {
    // Adding an event listener directly
    if (!this.createRuleButton) {
      console.error('createRuleButton is undefined');
    } else {
      console.log('createRuleButton is defined:', this.createRuleButton);
    }
  
}

     createRule() {
    this.ruleService.createRule(this.ruleString).subscribe(
      response => this.output.push(`Create Rule Response: ${JSON.stringify(response)}`),
      error => this.output.push(`Error: ${error.message}`)
    );
  }

  combineRules() {
    const ids = this.ruleIds.split(',').map(id => parseInt(id.trim())).filter(id => !isNaN(id));
    this.ruleService.combineRules(ids).subscribe(
      response => this.output.push(`Combine Rules Response: ${JSON.stringify(response)}`),
      error => this.output.push(`Error: ${error.message}`)
    );
  }

  evaluateRule() {
    if (this.megaRuleId !== null) {
      try {
        const data = JSON.parse(this.dataJson);
        this.ruleService.evaluateRule(this.megaRuleId, data).subscribe(
          response => this.output.push(`Evaluate Rule Response: ${JSON.stringify(response)}`),
          error => this.output.push(`Error: ${error.message}`)
        );
      } catch (e) {
        this.output.push(`JSON Decode Error: ${(e as Error).message}`);
      }
    }
  }
  handleClick() {
    console.log('Button clicked using addEventListener');
    this.createRule();
  }

 
    }
  

