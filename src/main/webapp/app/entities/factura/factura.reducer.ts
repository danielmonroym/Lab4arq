import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IFactura, defaultValue } from 'app/shared/model/factura.model';

export const ACTION_TYPES = {
  FETCH_FACTURA_LIST: 'factura/FETCH_FACTURA_LIST',
  FETCH_FACTURA: 'factura/FETCH_FACTURA',
  CREATE_FACTURA: 'factura/CREATE_FACTURA',
  UPDATE_FACTURA: 'factura/UPDATE_FACTURA',
  DELETE_FACTURA: 'factura/DELETE_FACTURA',
  RESET: 'factura/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IFactura>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type FacturaState = Readonly<typeof initialState>;

// Reducer

export default (state: FacturaState = initialState, action): FacturaState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_FACTURA_LIST):
    case REQUEST(ACTION_TYPES.FETCH_FACTURA):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_FACTURA):
    case REQUEST(ACTION_TYPES.UPDATE_FACTURA):
    case REQUEST(ACTION_TYPES.DELETE_FACTURA):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_FACTURA_LIST):
    case FAILURE(ACTION_TYPES.FETCH_FACTURA):
    case FAILURE(ACTION_TYPES.CREATE_FACTURA):
    case FAILURE(ACTION_TYPES.UPDATE_FACTURA):
    case FAILURE(ACTION_TYPES.DELETE_FACTURA):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_FACTURA_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_FACTURA):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_FACTURA):
    case SUCCESS(ACTION_TYPES.UPDATE_FACTURA):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_FACTURA):
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

const apiUrl = 'api/factura';

// Actions

export const getEntities: ICrudGetAllAction<IFactura> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_FACTURA_LIST,
  payload: axios.get<IFactura>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IFactura> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_FACTURA,
    payload: axios.get<IFactura>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IFactura> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_FACTURA,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IFactura> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_FACTURA,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IFactura> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_FACTURA,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
