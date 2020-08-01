export interface IAttachment {
  id?: number;
  name?: string;
  fileContentType?: string;
  file?: any;
  ticketTitle?: string;
  ticketId?: number;
}

export class Attachment implements IAttachment {
  constructor(
    public id?: number,
    public name?: string,
    public fileContentType?: string,
    public file?: any,
    public ticketTitle?: string,
    public ticketId?: number
  ) {}
}
