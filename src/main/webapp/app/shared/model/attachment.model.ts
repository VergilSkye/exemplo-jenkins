export interface IAttachment {
  id?: number;
  name?: string;
  fileContentType?: string;
  file?: any;
  ticketId?: number;
}

export class Attachment implements IAttachment {
  constructor(public id?: number, public name?: string, public fileContentType?: string, public file?: any, public ticketId?: number) {}
}
