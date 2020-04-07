import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IFactura } from 'app/shared/model/factura.model';
import { getEntities as getFacturas } from 'app/entities/factura/factura.reducer';
import { getEntity, updateEntity, createEntity, reset } from './vehiculo.reducer';
import { IVehiculo } from 'app/shared/model/vehiculo.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IVehiculoUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IVehiculoUpdateState {
  isNew: boolean;
  facturaId: string;
}

export class VehiculoUpdate extends React.Component<IVehiculoUpdateProps, IVehiculoUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      facturaId: '0',
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getFacturas();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { vehiculoEntity } = this.props;
      const entity = {
        ...vehiculoEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
      this.handleClose();
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/vehiculo');
  };

  render() {
    const { vehiculoEntity, facturas, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="concesionario4App.vehiculo.home.createOrEditLabel">
              <Translate contentKey="concesionario4App.vehiculo.home.createOrEditLabel">Create or edit a Vehiculo</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : vehiculoEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="vehiculo-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="marcaLabel" for="marca">
                    <Translate contentKey="concesionario4App.vehiculo.marca">marca</Translate>
                  </Label>
                  <AvField id="vehiculo-marca" type="text" name="marca" />
                </AvGroup>
                <AvGroup>
                  <Label id="modeloLabel" for="modelo">
                    <Translate contentKey="concesionario4App.vehiculo.modelo">modelo</Translate>
                  </Label>
                  <AvField id="vehiculo-modelo" type="text" name="modelo" />
                </AvGroup>
                <AvGroup>
                  <Label id="precioLabel" for="precio">
                    <Translate contentKey="concesionario4App.vehiculo.precio">precio</Translate>
                  </Label>
                  <AvField id="vehiculo-precio" type="text" name="precio" />
                </AvGroup>
                <AvGroup>
                  <Label for="factura.id">
                    <Translate contentKey="concesionario4App.vehiculo.factura">Factura</Translate>
                  </Label>
                  <AvInput id="vehiculo-factura" type="select" className="form-control" name="factura.id">
                    <option value="" key="0" />
                    {facturas
                      ? facturas.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/vehiculo" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />
                  &nbsp;
                  <span className="d-none d-md-inline">
                    <Translate contentKey="entity.action.back">Back</Translate>
                  </span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />
                  &nbsp;
                  <Translate contentKey="entity.action.save">Save</Translate>
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  facturas: storeState.factura.entities,
  vehiculoEntity: storeState.vehiculo.entity,
  loading: storeState.vehiculo.loading,
  updating: storeState.vehiculo.updating
});

const mapDispatchToProps = {
  getFacturas,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(VehiculoUpdate);
