import { Moment } from 'moment';
import { IAttachment } from 'app/shared/model/attachment.model';
import { ILabel } from 'app/shared/model/label.model';
import { Status } from 'app/shared/model/enumerations/status.model';
import { Type } from 'app/shared/model/enumerations/type.model';
import { Priority } from 'app/shared/model/enumerations/priority.model';

export interface ITicket {
  id?: number;
  title?: string;
  description?: string;
  dueDate?: Moment;
  date?: Moment;
  status?: Status;
  type?: Type;
  priority?: Priority;
  attachments?: IAttachment[];
  projectName?: string;
  projectId?: number;
  assignedToLogin?: string;
  assignedToId?: number;
  reportedByLogin?: string;
  reportedById?: number;
  labels?: ILabel[];
}

export class Ticket implements ITicket {
  constructor(
    public id?: number,
    public title?: string,
    public description?: string,
    public dueDate?: Moment,
    public date?: Moment,
    public status?: Status,
    public type?: Type,
    public priority?: Priority,
    public attachments?: IAttachment[],
    public projectName?: string,
    public projectId?: number,
    public assignedToLogin?: string,
    public assignedToId?: number,
    public reportedByLogin?: string,
    public reportedById?: number,
    public labels?: ILabel[]
  ) {}
}
