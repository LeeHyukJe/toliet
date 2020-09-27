export default {
  register (detail) {
    return new Promise((resolve, reject) => {
      detail.emailAddress === 'sunny@local'
      ? resolve({result: 'sunny'})
        : reject(new Error('User Already exist'))
    })
  }
}
