import { IFactura } from './factura.model';

export interface IUsuario {
  id?: number;
  id2?: number;
  nombre?: string;
  email?: string;
  pass?: string;
  factura?: IFactura;
}

export const defaultValue: Readonly<IUsuario> = {};
