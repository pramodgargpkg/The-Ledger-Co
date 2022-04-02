package main.Service;

import main.Response.BaseResponse;

public interface IRequestService {
    BaseResponse service() throws Exception;
}
