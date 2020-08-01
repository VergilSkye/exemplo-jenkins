import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IComment } from 'app/shared/model/comment.model';
import { CommentService } from './comment.service';

@Component({
  templateUrl: './comment-delete-dialog.component.html',
})
export class CommentDeleteDialogComponent {
  comment?: IComment;

  constructor(protected commentService: CommentService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.commentService.delete(id).subscribe(() => {
      this.eventManager.broadcast('commentListModification');
      this.activeModal.close();
    });
  }
}
