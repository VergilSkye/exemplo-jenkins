import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IComment, Comment } from 'app/shared/model/comment.model';
import { CommentService } from './comment.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';
import { ITicket } from 'app/shared/model/ticket.model';
import { TicketService } from 'app/entities/ticket/ticket.service';

type SelectableEntity = IUser | ITicket | IComment;

@Component({
  selector: 'jhi-comment-update',
  templateUrl: './comment-update.component.html',
})
export class CommentUpdateComponent implements OnInit {
  isSaving = false;
  users: IUser[] = [];
  tickets: ITicket[] = [];
  comments: IComment[] = [];

  editForm = this.fb.group({
    id: [],
    date: [],
    text: [],
    createById: [],
    ticketId: [],
    childId: [],
  });

  constructor(
    protected commentService: CommentService,
    protected userService: UserService,
    protected ticketService: TicketService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ comment }) => {
      if (!comment.id) {
        const today = moment().startOf('day');
        comment.date = today;
      }

      this.updateForm(comment);

      this.userService.query().subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body || []));

      this.ticketService.query().subscribe((res: HttpResponse<ITicket[]>) => (this.tickets = res.body || []));

      this.commentService.query().subscribe((res: HttpResponse<IComment[]>) => (this.comments = res.body || []));
    });
  }

  updateForm(comment: IComment): void {
    this.editForm.patchValue({
      id: comment.id,
      date: comment.date ? comment.date.format(DATE_TIME_FORMAT) : null,
      text: comment.text,
      createById: comment.createById,
      ticketId: comment.ticketId,
      childId: comment.childId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const comment = this.createFromForm();
    if (comment.id !== undefined) {
      this.subscribeToSaveResponse(this.commentService.update(comment));
    } else {
      this.subscribeToSaveResponse(this.commentService.create(comment));
    }
  }

  private createFromForm(): IComment {
    return {
      ...new Comment(),
      id: this.editForm.get(['id'])!.value,
      date: this.editForm.get(['date'])!.value ? moment(this.editForm.get(['date'])!.value, DATE_TIME_FORMAT) : undefined,
      text: this.editForm.get(['text'])!.value,
      createById: this.editForm.get(['createById'])!.value,
      ticketId: this.editForm.get(['ticketId'])!.value,
      childId: this.editForm.get(['childId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IComment>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
