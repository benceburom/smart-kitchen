import {Injectable} from '@angular/core';
import {
    HttpRequest,
    HttpHandler,
    HttpEvent,
    HttpInterceptor,
    HttpHeaders
} from '@angular/common/http';
import {AuthenticationService} from '../services/authentication.service';
import {Observable, from} from 'rxjs';
import {switchMap} from 'rxjs/operators';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

    constructor(private authenticationService: AuthenticationService) {
    }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return from(this.authenticationService.getToken()).pipe(switchMap(result => {
            const token = result.toString();
            const headers = new HttpHeaders({
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json'
            });
            const clonedRequest = request.clone({headers});
            return next.handle(clonedRequest);
        }));
    }
}
