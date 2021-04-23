import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { Character } from './character';
import { Race } from './race';

@Injectable({
  providedIn: 'root'
})
export class CharacterService {

  private absoluteEvil: Character = { name: 'Sauron', race: Race.Maiar };

  constructor(private http: HttpClient) { }

  getCharacters(): Observable<Character[]> {
    return this.http.get<Character[]>('api/characters')
      .pipe(
        catchError(error => of([ this.absoluteEvil ]))
      );
  }

}
