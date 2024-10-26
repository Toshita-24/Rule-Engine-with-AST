import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';



@Injectable({
  providedIn: 'root'
})
export class RuleService {
  modifyRule(modifyRuleId: number, newRuleString: string) {
    throw new Error('Method not implemented.');
  }
    PATH_OF_API = 'http://localhost:8080';
  

  constructor(private http: HttpClient) { }
  createRule(ruleString: string): Observable<any> {
    return this.http.post(`${this.PATH_OF_API}/create`, { rule_string: ruleString });
  }
  


  combineRules(ruleIds: number[]): Observable<any> {
    return this.http.post(`${this. PATH_OF_API}/combine`, { rule_ids: ruleIds });
  }

  evaluateRule(ruleId: number, data: any): Observable<any> {
    return this.http.post(`${this. PATH_OF_API}/evaluate`, { rule_id: ruleId, data });
  }

}











  

  