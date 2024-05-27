<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <!--  删除/修改模态窗口-->
    <div class="modal fade" id="oper" tabindex="-1" role="dialog"
        aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content modal-user">
                <div class="modal-header" style="border-bottom: 1px solid #FFFFFF;">
                    <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">&times;</button>

                </div>
                <div class="modal-body" style="text-align: center;">操作成功</div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-dismiss="modal"
                        onclick="">确定</button>
                    
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal -->
    </div>
    
    
   <div class="modal fade" id="isDelete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content modal-user">
            <div class="modal-header" style="border-bottom: 1px solid #FFFFFF;">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                
            </div>
            <div class="modal-body" style="text-align: center;">确定删除？</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
  </div>