import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IVehiculo, defaultValue } from 'app/shared/model/vehiculo.model';

export const ACTION_TYPES = {
  FETCH_VEHICULO_LIST: 'vehiculo/FETCH_VEHICULO_LIST',
  FETCH_VEHICULO: 'vehiculo/FETCH_VEHICULO',
  CREATE_VEHICULO: 'vehiculo/CREATE_VEHICULO',
  UPDATE_VEHICULO: 'vehiculo/UPDATE_VEHICULO',
  DELETE_VEHICULO: 'vehiculo/DELETE_VEHICULO',
  RESET: 'vehiculo/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IVehiculo>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type VehiculoState = Readonly<typeof initialState>;

// Reducer

export default (state: VehiculoState = initialState, action): VehiculoState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_VEHICULO_LIST):
    case REQUEST(ACTION_TYPES.FETCH_VEHICULO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_VEHICULO):
    case REQUEST(ACTION_TYPES.UPDATE_VEHICULO):
    case REQUEST(ACTION_TYPES.DELETE_VEHICULO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_VEHICULO_LIST):
    case FAILURE(ACTION_TYPES.FETCH_VEHICULO):
    case FAILURE(ACTION_TYPES.CREATE_VEHICULO):
    case FAILURE(ACTION_TYPES.UPDATE_VEHICULO):
    case FAILURE(ACTION_TYPES.DELETE_VEHICULO):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_VEHICULO_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_VEHICULO):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_VEHICULO):
    case SUCCESS(ACTION_TYPES.UPDATE_VEHICULO):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_VEHICULO):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/vehiculo';

// Actions

export const getEntities: ICrudGetAllAction<IVehiculo> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_VEHICULO_LIST,
  payload: axios.get<IVehiculo>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IVehiculo> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_VEHICULO,
    payload: axios.get<IVehiculo>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IVehiculo> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_VEHICULO,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IVehiculo> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_VEHICULO,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IVehiculo> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_VEHICULO,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
