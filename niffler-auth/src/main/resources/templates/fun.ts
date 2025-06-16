export async function mockJSBridge(
 context: BrowserContext, token: string): Promise<void> {
    await context.addInitScript(
        async (arg) => { window.AndroidInterface = {
                getToken: () => {
                    return Promise.resolve(arg.token)
                },
            };
        },
        { token: token,});
}

