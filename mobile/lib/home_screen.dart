import 'package:flutter/material.dart';
import '../../detalhes_nfse.dart';
import '../../criar_nfse.dart';

class HomeScreen extends StatefulWidget {
  final String nome;

  const HomeScreen({
    super.key,
    required this.nome,
  });

  @override
  HomeScreenState createState() => HomeScreenState();
}

class HomeScreenState extends State<HomeScreen> {
  bool _isLoading = true;

  @override
  void initState() {
    super.initState();
    Future.delayed(const Duration(seconds: 2), () {
      setState(() {
        _isLoading = false;
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Minhas Notas Fiscais'),
        centerTitle: true,
      ),
      body: Container(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(
              'Bem-vindo ${widget.nome}',
              style: const TextStyle(
                fontWeight: FontWeight.bold,
                fontSize: 18.0,
              ),
            ),
            const SizedBox(height: 16.0),
            _isLoading
                ? const Center(child: CircularProgressIndicator())
                : Expanded(
                    child: RefreshIndicator(
                      onRefresh: () async {
                        setState(() {
                          _isLoading = true;
                        });
                        await Future.delayed(const Duration(seconds: 2));
                        setState(() {
                          _isLoading = false;
                        });
                      },
                      child: ListView.builder(
                        itemCount: 10,
                        itemBuilder: (BuildContext context, int index) {
                          return Card(
                            elevation: 2.0,
                            child: ListTile(
                              title: Text('Nota Fiscal ${index + 1}'),
                              subtitle: const Text('Data: 09/05/2023'),
                              trailing: const Icon(Icons.arrow_forward_ios),
                              onTap: () {
                                Navigator.push(
                                  context,
                                  MaterialPageRoute(
                                    builder: (context) => DetalhesNFSE(
                                      numeroNotaFiscal: "${index + 1}",
                                      data: "09/05/2023",
                                      valorTotal: 50,
                                      razaoSocial: "Exemplo Indústrias Ltda",
                                      pessoaJuridica: false,
                                      inscricaoMunicipal: "9999999",
                                    ),
                                  ),
                                );
                              },
                            ),
                          );
                        },
                      ),
                    ),
                  ),
          ],
        ),
      ),
      bottomNavigationBar: BottomAppBar(
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          children: [
            ElevatedButton(
              child: const Text('Atualizar'),
              onPressed: () {
                setState(() {
                  _isLoading = true;
                });
                Future.delayed(const Duration(seconds: 2), () {
                  setState(() {
                    _isLoading = false;
                  });
                });
              },
            ),
            ElevatedButton(
              child: const Text('Adicionar Nota Fiscal'),
              onPressed: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(
                    builder: (context) => const CriarNFSE(),
                  ),
                );
              },
            ),
          ],
        ),
      ),
    );
  }
}
