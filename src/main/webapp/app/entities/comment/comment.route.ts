import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IComment, Comment } from 'app/shared/model/comment.model';
import { CommentService } from './comment.service';
import { CommentComponent } from './comment.component';
import { CommentDetailComponent } from './comment-detail.component';
import { CommentUpdateComponent } from './comment-update.component';

@Injectable({ providedIn: 'root' })
export class CommentResolve implements Resolve<IComment> {
  constructor(private service: CommentService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IComment> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((comment: HttpResponse<Comment>) => {
          if (comment.body) {
            return of(comment.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Comment());
  }
}

export const commentRoute: Routes = [
  {
    path: '',
    component: CommentComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'Comments',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CommentDetailComponent,
    resolve: {
      comment: CommentResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Comments',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CommentUpdateComponent,
    resolve: {
      comment: CommentResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Comments',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CommentUpdateComponent,
    resolve: {
      comment: CommentResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Comments',
    },
    canActivate: [UserRouteAccessService],
  },
];
