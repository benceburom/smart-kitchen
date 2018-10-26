import {Injectable} from '@angular/core';
import {
    HttpRequest,
    HttpHandler,
    HttpEvent,
    HttpInterceptor,
    HttpHeaders
} from '@angular/common/http';
import {AuthenticationService} from '../services/authentication.service';
import {Observable} from 'rxjs';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
    token: String;

    constructor(private authenticationService: AuthenticationService) {
    }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        this.token = this.authenticationService.getToken();
        console.log('interceptor ' + this.token);
        const headers = new HttpHeaders({
            'Authorization': `Bearer ${this.token}`,
            'Content-Type': 'application/json'
        });
        const clonedRequest = request.clone({headers});
        return next.handle(clonedRequest);
    }
}
