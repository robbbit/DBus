/**
 * @author 戎晓伟
 * @description  基本信息设置
 */

import React, {Component, PropTypes} from 'react'
import {Form, Input, message, Modal, Select} from 'antd'
import {intlMessage} from '@/app/i18n'
// 导入样式
import {UPDATE_SINKER_TABLE_API} from '@/app/containers/SinkManage/api'
import Request from '@/app/utils/request'

const FormItem = Form.Item
const Option = Select.Option
@Form.create({warppedComponentRef: true})
export default class SinkerTableForm extends Component {
  constructor(props) {
    super(props)
    this.formItemLayout = {
      labelCol: {span: 4},
      wrapperCol: {span: 12}
    }
  }

  /**
   * @deprecated 提交数据
   */
  handleSubmit = () => {
    const {
      record,
      onSearch,
      onClose,
      searchParams
    } = this.props
    this.props.form.validateFieldsAndScroll((err, values) => {
      if (!err) {
        Request(UPDATE_SINKER_TABLE_API, {
          data: {
            ...record,
            ...values
          },
          method: 'post'
        })
          .then(res => {
            if (res && res.status === 0) {
              onClose(false)
              onSearch(searchParams)
            } else {
              message.warn(res.message)
            }
            this.setState({loading: false})
          })
          .catch(error => {
            error.response && error.response.data && error.response.data.message
              ? message.error(error.response.data.message)
              : message.error(error.message)
            this.setState({loading: false})
          })
      }
    })
  }
  /**
   * @deprecated input placeholder
   */
  handlePlaceholder = fun => id =>
    fun({
      id: 'app.components.input.placeholder',
      valus: {
        name: fun({id})
      }
    })

  render() {
    const {getFieldDecorator} = this.props.form
    const {key, visible, onClose, record} = this.props
    const localeMessage = intlMessage(this.props.locale, this.formMessage)
    const placeholder = this.handlePlaceholder(localeMessage)
    return (
      <Modal
        key={key}
        visible={visible}
        maskClosable={false}
        width={'800px'}
        style={{top: 60}}
        onCancel={() => onClose(false)}
        onOk={this.handleSubmit}
        confirmLoading={false}
        title="修改Sinker表"
      >
        <Form autoComplete="off" layout="horizontal">
          <FormItem label='ID' {...this.formItemLayout}>
            {getFieldDecorator('id', {
              initialValue: (record && record.id),
            })(
              <Input
                type="text"
                placeholder={'ID'}
                disabled={true}
              />
            )}
          </FormItem>
          <FormItem label='Sinker名称' {...this.formItemLayout}>
            {getFieldDecorator('sinkerName', {
              initialValue: (record && record.sinkerName),
            })(
              <Input
                type="text"
                placeholder={'sinkerName'}
                disabled={true}
              />
            )}
          </FormItem>
          <FormItem label='数据源名称' {...this.formItemLayout}>
            {getFieldDecorator('dsName', {
              initialValue: (record && record.dsName),
            })(
              <Input
                type="text"
                placeholder={'dsName'}
                disabled={true}
              />
            )}
          </FormItem>
          <FormItem label='Schema名称' {...this.formItemLayout}>
            {getFieldDecorator('schemaName', {
              initialValue: (record && record.schemaName),
            })(
              <Input
                type="text"
                placeholder={'schemaName'}
                disabled={true}
              />
            )}
          </FormItem>
          <FormItem label='表名称' {...this.formItemLayout}>
            {getFieldDecorator('tableName', {
              initialValue: (record && record.tableName),
            })(
              <Input
                type="text"
                placeholder={'tableName'}
                disabled={true}
              />
            )}
          </FormItem>
          <FormItem label='描述' {...this.formItemLayout}>
            {getFieldDecorator('description', {
              initialValue: (record && record.description),
            })(
              <Input
                type="text"
                placeholder={'description'}
              />
            )}
          </FormItem>
        </Form>
      </Modal>
    )
  }
}

SinkerTableForm.propTypes = {
  locale: PropTypes.any,
  form: PropTypes.object,
  sink: PropTypes.object,
  modalStatus: PropTypes.string,
  visibal: PropTypes.bool,
  onCloseModal: PropTypes.func
}
