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
import { getEntity, updateEntity, createEntity, reset } from './usuario.reducer';
import { IUsuario } from 'app/shared/model/usuario.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IUsuarioUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IUsuarioUpdateState {
  isNew: boolean;
  facturaId: string;
}

export class UsuarioUpdate extends React.Component<IUsuarioUpdateProps, IUsuarioUpdateState> {
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
      const { usuarioEntity } = this.props;
      const entity = {
        ...usuarioEntity,
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
    this.props.history.push('/entity/usuario');
  };

  render() {
    const { usuarioEntity, facturas, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="concesionario4App.usuario.home.createOrEditLabel">
              <Translate contentKey="concesionario4App.usuario.home.createOrEditLabel">Create or edit a Usuario</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : usuarioEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="usuario-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="idLabel" for="id">
                    <Translate contentKey="concesionario4App.usuario.id">id</Translate>
                  </Label>
                  <AvField
                    id="usuario-id"
                    type="string"
                    className="form-control"
                    name="id"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="nombreLabel" for="nombre">
                    <Translate contentKey="concesionario4App.usuario.nombre">nombre</Translate>
                  </Label>
                  <AvField
                    id="usuario-nombre"
                    type="text"
                    name="nombre"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="emailLabel" for="email">
                    <Translate contentKey="concesionario4App.usuario.email">email</Translate>
                  </Label>
                  <AvField
                    id="usuario-email"
                    type="text"
                    name="email"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="passLabel" for="pass">
                    <Translate contentKey="concesionario4App.usuario.pass">pass</Translate>
                  </Label>
                  <AvField
                    id="usuario-pass"
                    type="text"
                    name="pass"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="factura.id">
                    <Translate contentKey="concesionario4App.usuario.factura">Factura</Translate>
                  </Label>
                  <AvInput id="usuario-factura" type="select" className="form-control" name="factura.id">
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
                <Button tag={Link} id="cancel-save" to="/entity/usuario" replace color="info">
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
  usuarioEntity: storeState.usuario.entity,
  loading: storeState.usuario.loading,
  updating: storeState.usuario.updating
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
)(UsuarioUpdate);
