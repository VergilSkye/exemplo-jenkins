import { Moment } from 'moment';

export interface IComment {
  id?: number;
  date?: Moment;
  text?: string;
  parents?: IComment[];
  loginId?: number;
  ticketTitle?: string;
  ticketId?: number;
  childId?: number;
}

export class Comment implements IComment {
  constructor(
    public id?: number,
    public date?: Moment,
    public text?: string,
    public parents?: IComment[],
    public loginId?: number,
    public ticketTitle?: string,
    public ticketId?: number,
    public childId?: number
  ) {}
}
