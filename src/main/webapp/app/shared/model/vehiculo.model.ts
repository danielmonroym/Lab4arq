import { IFactura } from './factura.model';

export interface IVehiculo {
  id?: number;
  marca?: string;
  modelo?: string;
  precio?: string;
  foto?: string;
  factura?: IFactura;
}

export const defaultValue: Readonly<IVehiculo> = {};
