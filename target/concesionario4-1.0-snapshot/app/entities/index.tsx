import React from 'react';
import { Switch } from 'react-router-dom';

// tslint:disable-next-line:no-unused-variable
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';
import Usuario from './usuario';
import Factura from './factura';
import Vehiculo from './vehiculo';
/* needle-add-route-import - add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}/usuario`} component={Usuario} />
      <ErrorBoundaryRoute path={`${match.url}/factura`} component={Factura} />
      <ErrorBoundaryRoute path={`${match.url}/vehiculo`} component={Vehiculo} />
      {/* needle-add-route-path - routes here */}
    </Switch>
  </div>
);

export default Routes;
