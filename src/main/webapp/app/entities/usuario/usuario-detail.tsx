import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './usuario.reducer';
import { IUsuario } from 'app/shared/model/usuario.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IUsuarioDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class UsuarioDetail extends React.Component<IUsuarioDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { usuarioEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="concesionario4App.usuario.detail.title">Usuario</Translate> [<b>{usuarioEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="id">
                <Translate contentKey="concesionario4App.usuario.id">id</Translate>
              </span>
            </dt>
            <dd>{usuarioEntity.id}</dd>
            <dt>
              <span id="nombre">
                <Translate contentKey="concesionario4App.usuario.nombre">nombre</Translate>
              </span>
            </dt>
            <dd>{usuarioEntity.nombre}</dd>
            <dt>
              <span id="email">
                <Translate contentKey="concesionario4App.usuario.email">email</Translate>
              </span>
            </dt>
            <dd>{usuarioEntity.email}</dd>
            <dt>
              <span id="pass">
                <Translate contentKey="concesionario4App.usuario.pass">pass</Translate>
              </span>
            </dt>
            <dd>{usuarioEntity.pass}</dd>
            <dt>
              <Translate contentKey="concesionario4App.usuario.factura">Factura</Translate>
            </dt>
            <dd>{usuarioEntity.factura ? usuarioEntity.factura.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/usuario" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/usuario/${usuarioEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ usuario }: IRootState) => ({
  usuarioEntity: usuario.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(UsuarioDetail);
