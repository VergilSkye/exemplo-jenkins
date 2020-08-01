import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'project',
        loadChildren: () => import('./project/project.module').then(m => m.ExemploProjectModule),
      },
      {
        path: 'label',
        loadChildren: () => import('./label/label.module').then(m => m.ExemploLabelModule),
      },
      {
        path: 'ticket',
        loadChildren: () => import('./ticket/ticket.module').then(m => m.ExemploTicketModule),
      },
      {
        path: 'attachment',
        loadChildren: () => import('./attachment/attachment.module').then(m => m.ExemploAttachmentModule),
      },
      {
        path: 'comment',
        loadChildren: () => import('./comment/comment.module').then(m => m.ExemploCommentModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class ExemploEntityModule {}
