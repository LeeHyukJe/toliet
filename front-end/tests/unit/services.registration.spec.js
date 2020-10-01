import registrationService from '@/services/registration'
import moxios from 'moxios'

describe('services/registration', () => {
  beforeEach(() => {
    moxios.install()
  })

  afterEach(() => {
    moxios.uninstall()
  })

  it('should pass the response to caller when request succeeded', () => {
    expect.assertions(2)
    moxios.wait(() => {
      let request = moxios.requests.mostRecent()
      expect(request).toBeTruthy()
      request.respondWith({
        status: 200,
        response: {result: 'success'}
      })
    })

    return registrationService.register().then(data => {
      expect(data.result).toEqual('success')
    })
  })

  it('should propagate the error to caller when request failed', () => {
    expect.assertions(2)
    moxios.wait(() => {
      let request = moxios.requests.mostRecent()
      expect(request).toBeTruthy()
      request.reject({
        status: 400,
        response: {message: 'Bad Request'}
      })
    })

    return registrationService.register().catch(error => {
      expect(error.response.message).toEqual('Bad Request')
    })
  })

  it('should call `/registrations` API', () => {
    expect.assertions(1)
    moxios.wait(() => {
      let request = moxios.requests.mostRecent()
      expect(request.url).toEqual('/registrations')
      request.respondWith({
        status: 200,
        response: {result: 'success'}
      })
    })
    return registrationService.register()
  })
})
